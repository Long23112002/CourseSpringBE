package com.example.coursel_be.service;

import com.example.coursel_be.request.chapter.ChapterRequest;
import com.example.coursel_be.request.chapter.ChapterUpdateRequest;

public interface ChapterService {

    String saveChapter(ChapterRequest chapterRequest);

    String updateChapter(ChapterUpdateRequest chapterUpdateRequest);
}
