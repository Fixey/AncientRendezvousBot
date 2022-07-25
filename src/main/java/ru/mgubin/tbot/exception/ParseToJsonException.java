package ru.mgubin.tbot.exception;

public class ParseToJsonException extends RuntimeException{
    public ParseToJsonException() {
        super("Can't parse to JSon!");
    }
}
