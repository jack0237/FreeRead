//package com.sklfgroup.auxillium.rest.controllers;
//
//
//import com.sklfgroup.auxillium.dao.entities.ExampleEntity;
//import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
//import com.sklfgroup.auxillium.exceptions.NotFoundException;
//import com.sklfgroup.auxillium.rest.api.LessonApi;
//import com.sklfgroup.auxillium.rest.dto.requests.lesson.LessonCreateRequest;
//import com.sklfgroup.auxillium.rest.dto.responses.chapter.ChapterResponse;
//import com.sklfgroup.auxillium.rest.dto.responses.example.ExampleResponse;
//import com.sklfgroup.auxillium.rest.dto.responses.lesson.LessonResponse;
//import com.sklfgroup.auxillium.rest.dto.responses.subject.SubjectResponse;
//import com.sklfgroup.auxillium.services.LessonService;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@CrossOrigin
//@RestController
//@RequiredArgsConstructor
//public class LessonController implements LessonApi {
//
//    private final LessonService lessonService;
//
//    @Override
//    public ResponseEntity<LessonResponse> createLesson(LessonCreateRequest request) throws AlreadyExistsException {
//        return ResponseEntity.ok(new LessonResponse(lessonService.save(request.toEntity())));
//    }
//
//    @Override
//    public ResponseEntity<LessonResponse> deleteLesson(String lessonId) {
//        return ResponseEntity.ok(new LessonResponse(lessonService.delete(lessonId)));
//    }
//
//
//    @Override
//        public ResponseEntity<LessonResponse> updateLesson( String lessonId, String LessonName, String CLesson) {
//            return ResponseEntity.ok(new LessonResponse(lessonService.update(lessonId, LessonName, CLesson)));
//        }
//
//    @Override
//    public ResponseEntity<LessonResponse> findLesson(String nameLesson) {
//        return ResponseEntity.ok(new LessonResponse(lessonService.find(nameLesson)));
//    }
//
//    @Override
//    public ResponseEntity<LessonResponse> activate(String exampleId) throws NotFoundException {
//        return ResponseEntity.ok(new LessonResponse( lessonService.activate(exampleId)));
//
//    }
//
//    @Override
//    public ResponseEntity<LessonResponse> deactivate(String exampleId) {
//        return ResponseEntity.ok(new LessonResponse( lessonService.deactivate(exampleId)));
//    }
//
//
//}
