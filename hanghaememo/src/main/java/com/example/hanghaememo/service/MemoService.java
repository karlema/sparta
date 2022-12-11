package com.example.hanghaememo.service;

import com.example.hanghaememo.dto.MemoRequestDto;
import com.example.hanghaememo.entity.Memo;
import com.example.hanghaememo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    @Transactional
    public Memo createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return memo;
    }

    @Transactional(readOnly = true)
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Long update(Long id,String pwd,MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        if(pwd.equals(requestDto.getPwd()))
        {
            memo.update(requestDto);
            System.out.println("업데이트 완료");
        }
        else
        {
            throw new IllegalArgumentException("패스워드가 틀립니다. 수정할 수 없습니다.");
        }
        return memo.getId();
    }

    @Transactional
    public Long deleteMemo(Long id,String pwd,MemoRequestDto requestDto) {
        if(pwd.equals(requestDto.getPwd()))
        {
            memoRepository.deleteById(id);
            System.out.println("삭제완료");
        }
        else
        {
            throw new IllegalArgumentException("패스워드가 틀립니다. 지울 수 없습니다.");
        }
        return id;
    }
}
