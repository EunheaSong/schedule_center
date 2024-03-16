package com.seed_planner.schedule_center.plan.domain;

import lombok.Getter;

@Getter
public class Memo {
//    @Column(length = 200)
    private String contents;

    public Memo(String contents) {
        setContents(contents);
    }

    private void setContents(String contents) {
        if (contents.length() > 200)
            throw new IllegalArgumentException("memo length exceeded.\\n" + "param length : " + contents.length());
        this.contents = contents;
    }
}
