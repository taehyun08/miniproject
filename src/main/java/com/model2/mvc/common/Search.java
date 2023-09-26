package com.model2.mvc.common;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Search {

    private int currentPage;
    String searchCondition;
    String searchKeyword;
    String orderBy;
    int pageUnit;
    int startRowNum;
    int endRowNum;

}