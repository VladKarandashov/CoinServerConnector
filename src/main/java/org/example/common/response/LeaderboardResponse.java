package org.example.common.response;

import lombok.NoArgsConstructor;
import org.example.common.dto.UserScore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaderboardResponse {

    private List<UserScore> userScoreList;
}
