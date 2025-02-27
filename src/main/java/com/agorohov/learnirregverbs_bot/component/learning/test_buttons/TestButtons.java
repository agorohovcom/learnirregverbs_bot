package com.agorohov.learnirregverbs_bot.component.learning.test_buttons;

import java.util.Map;

public class TestButtons {

    private final Map<Integer, String[]> buttons;

    public TestButtons(Map<Integer, String[]> buttons) {
        this.buttons = buttons;
    }

    // нужно обработать некорректный индекс
    public String getButtonText(int index) {
        return buttons.get(index)[0];
    }
    
    // нужно обработать некорректный индекс
    public String getCallbackData(int index) {
        return buttons.get(index)[1];
    }
}
