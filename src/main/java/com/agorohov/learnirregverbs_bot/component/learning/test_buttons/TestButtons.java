package com.agorohov.learnirregverbs_bot.component.learning.test_buttons;

import java.util.Map;

public class TestButtons {
    
    private Map<Integer, String[]> buttons;
    
    public TestButtons(Map buttons){
        this.buttons = buttons;
    }

    public String getButtonText(int index) {
        return buttons.get(index)[0];
    }
    
    public String getCallbackData(int index) {
        return buttons.get(index)[1];
    }
}