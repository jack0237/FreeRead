package com.sklfgroup.auxillium.rest.controllers;


import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.exceptions.NotFoundException;
import com.sklfgroup.auxillium.rest.api.ChapterApi;
import com.sklfgroup.auxillium.rest.dto.requests.chapter.ChapterCreateRequest;
import com.sklfgroup.auxillium.rest.dto.responses.chapter.ChapterResponse;
import com.sklfgroup.auxillium.services.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequiredArgsConstructor
public class OuvrageControl implements ChapterApi {

    private final ChapterService chapterService;

    @Override
    public ResponseEntity<ChapterResponse> createChapter(ChapterCreateRequest request) throws AlreadyExistsException {
        return ResponseEntity.ok(new ChapterResponse(chapterService.save(request.toEntity())));
    }
    @Override
    public ResponseEntity<ChapterResponse> deleteChapter(String chapterId) {
        return ResponseEntity.ok(new ChapterResponse(chapterService.delete(chapterId)));
    }
    @Override
    public ResponseEntity<ChapterResponse> updateChapter( String chapterId, String nameChapter) {
        return ResponseEntity.ok(new ChapterResponse(chapterService.update(chapterId, nameChapter)));
    }

    @Override
    public ResponseEntity<ChapterResponse> findChapter(String nameChapter) {
        return ResponseEntity.ok(new ChapterResponse(chapterService.find(nameChapter)));
    }

    @Override
    public ResponseEntity<ChapterResponse> activate(String chapterId) throws NotFoundException {
        return ResponseEntity.ok(new ChapterResponse(chapterService.activate(chapterId)));

    }

    @Override
    public ResponseEntity<ChapterResponse> deactivate(String chapterId) {
        return ResponseEntity.ok(new ChapterResponse(chapterService.deactivate(chapterId)));
    }

}
