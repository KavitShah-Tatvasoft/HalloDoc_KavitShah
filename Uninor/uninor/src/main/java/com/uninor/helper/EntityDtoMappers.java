package com.uninor.helper;

import com.uninor.dto.SimSuggestionsDto;
import com.uninor.model.SimCard;

import java.util.ArrayList;
import java.util.List;

public class EntityDtoMappers {

    public static List<SimSuggestionsDto> simSuggestionsDtoMapper(List<SimCard> simCardList){
        List<SimSuggestionsDto> simSuggestionsDtoList = new ArrayList<SimSuggestionsDto>();

        for (SimCard simCard : simCardList) {
            SimSuggestionsDto simSuggestionsDto = new SimSuggestionsDto();
            simSuggestionsDto.setSimCardId(simCard.getSimCardId());
            simSuggestionsDto.setPhoneNumber(simCard.getPhoneNumber());
            simSuggestionsDtoList.add(simSuggestionsDto);
        }

        return simSuggestionsDtoList;
    }

}
