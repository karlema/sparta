package com.example.hanghaememo.dto;
import com.example.hanghaememo.entity.Memo;
public class MemoResponseDto {
    private String username;
    private String contents;
    private String title;
    private String pwd;
    public MemoResponseDto(Memo memo)
    {
        this.title = memo.getTitle();
        this.contents = memo.getContents();
        this.username = memo.getUsername();
    }
}
