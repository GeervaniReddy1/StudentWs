package com.student.service;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StudentEndPointsTesst {

    @Test
    public void setUp() throws Exception {
        List mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();

    }

}
