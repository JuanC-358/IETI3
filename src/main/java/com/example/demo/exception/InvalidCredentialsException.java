package com.example.demo.exception;

import javax.ws.rs.InternalServerErrorException;

import com.example.demo.data.ErrorCodeEnum;
import com.example.demo.dto.ServerErrorResponseDto;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends InternalServerErrorException
{
    public InvalidCredentialsException() {
        super(new ServerErrorResponseDto("User not found", ErrorCodeEnum.USER_NOT_FOUND, HttpStatus.NOT_FOUND).getMessage());
    }
}
