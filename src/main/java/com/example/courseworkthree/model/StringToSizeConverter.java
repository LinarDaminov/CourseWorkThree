package com.example.courseworkthree.model;


import com.example.courseworkthree.model.Size;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToSizeConverter implements Converter<String, Size> {

    @Override
    public Size convert(String source) {
        return Size.forValues(source);
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }


}