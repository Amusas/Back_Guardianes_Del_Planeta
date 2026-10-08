package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.model.Lesson;
import com.guardianesdelplaneta.domain.port.in.GetLessonsUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.LessonResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.LessonRestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.guardianesdelplaneta.infrastructure.security.jwt.JwtTokenProvider;

@WebMvcTest(LessonController.class)
@AutoConfigureMockMvc(addFilters = false)
class LessonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private GetLessonsUseCase getLessonsUseCase;

    @MockBean
    private LessonRestMapper lessonRestMapper;

    @Test
    void getActiveLessons_ReturnsListOfLessons() throws Exception {
        Lesson lessonMock = mock(Lesson.class);
        LessonResponseDto dtoMock = mock(LessonResponseDto.class);

        when(getLessonsUseCase.getActiveLessonsByEcosystemId(1L)).thenReturn(List.of(lessonMock));
        when(lessonRestMapper.toResponseDto(any())).thenReturn(dtoMock);

        mockMvc.perform(get("/api/v1/ecosystems/1/lessons")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getLessonById_ReturnsLesson() throws Exception {
        Lesson lessonMock = mock(Lesson.class);
        LessonResponseDto dtoMock = mock(LessonResponseDto.class);

        when(getLessonsUseCase.getLessonById(1L)).thenReturn(lessonMock);
        when(lessonRestMapper.toResponseDto(any())).thenReturn(dtoMock);

        mockMvc.perform(get("/api/v1/lessons/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
