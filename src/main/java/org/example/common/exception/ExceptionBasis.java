package org.example.common.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum ExceptionBasis {

    // отбрасывается исключительно в непонятных технических случаях
    INTERNAL_SERVER_ERROR(1000),

    TOKEN_HEADER_NOT_FOUND(1101), // если в запросе нет токена - идём login
    WRONG_TOKEN(1102), // в БД нет user с таким токеном - вы зашли с другого устройства
    TOKEN_EXPIRED_NEED_LOGIN(1103), // токен есть, но старый - идём login
    WRONG_LOGIN_OR_PASSWORD(1104), // нет user с такими логин и пароль
    LOGIN_ALREADY_EXIST(1105),  // если при регистрации обнаружился user с таким же именем

    VALIDATION_ERROR(1200), // общая ошибка валидации
    VALIDATION_ERROR_USD(1201), // нельзя купить акций на неположительное кол-во денег
    VALIDATION_ERROR_ASSETS(1202), // нельзя продать неположительное кол-во акций

    NOT_FOUND(1301), // валюта не обнаружена - операция сейчас недоступна
    LACK_OF_RESOURCES(1302), // пользователь не может продать акций, больше чем у него есть
    ;

    private final int statusCode;

    ExceptionBasis(int statusCode) {
        this.statusCode = statusCode;
    }

    public static ExceptionBasis from(int statusCode) {
        return Arrays.stream(ExceptionBasis.values())
                .filter(basis -> basis.statusCode == statusCode)
                .findFirst()
                .orElseThrow(() -> {
                    System.out.println("not found status code = " + statusCode);
                    return new CoinServerException(INTERNAL_SERVER_ERROR);
                });

    }
}