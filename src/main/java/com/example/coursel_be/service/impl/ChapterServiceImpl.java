package com.example.coursel_be.service.impl;

import com.example.coursel_be.entity.Chapter;
import com.example.coursel_be.entity.Course;
import com.example.coursel_be.exceptions.AppException;
import com.example.coursel_be.exceptions.ErrorCode;
import com.example.coursel_be.repository.ChapterRepository;
import com.example.coursel_be.repository.CourseRepository;
import com.example.coursel_be.request.chapter.ChapterRequest;
import com.example.coursel_be.request.chapter.ChapterUpdateRequest;
import com.example.coursel_be.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;
    private final CourseRepository courseRepository;

    @Override
    public String saveChapter(ChapterRequest chapterRequest) {
        try {
            boolean exitsChapterTitle = chapterRepository.existsByTitle(chapterRequest.getTitle());

            if (!exitsChapterTitle) {
                chapterRepository.save(getChapter(chapterRequest));
                return "Chapter saved successfully";
            } else {
                return "Chapter already exists";
            }
        } catch (Exception e) {
            throw new AppException(ErrorCode.UNCATEGORIZED);
        }
    }

    @Override
    public String updateChapter(ChapterUpdateRequest chapterUpdateRequest) {
        try {
            Optional<Chapter> chapterOptional = chapterRepository.findById(chapterUpdateRequest.getIdChapter());
            if (chapterOptional.isEmpty()) {
                throw new AppException(ErrorCode.CHAPTER_NOT_FOUND);
            }
            Chapter chapter = chapterOptional.get();

            if (chapterUpdateRequest.getTitle() != null) {
                chapter.setTitle(chapterUpdateRequest.getTitle());
            }
            if (chapterUpdateRequest.getChapterSequence() != null) {
                chapter.setChapterSequence(chapterUpdateRequest.getChapterSequence());
            }

            if (chapterUpdateRequest.getDeleted() != null) {
                chapter.setDeleted(chapterUpdateRequest.getDeleted());
            }

            chapterRepository.save(chapter);
            return "Chapter updated successfully";
        } catch (Exception e) {
            throw new AppException(ErrorCode.UNCATEGORIZED);
        }
    }


    private Chapter getChapter(ChapterRequest chapterRequest) {
        Optional<Course> course = Optional.ofNullable(courseRepository.findById(chapterRequest.getCourseId()).orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND)));
        if (course.isEmpty()) {
            throw new AppException(ErrorCode.COURSE_NOT_FOUND);
        }
        Chapter chapter = new Chapter();
        chapter.setCourse(course.get());
        chapter.setChapterSequence(chapterRequest.getChapterSequence());
        chapter.setTitle(chapterRequest.getTitle());
        chapter.setDeleted(true);
        return chapter;
    }
}
