package ru.mgubin.tbot.db;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mgubin.tbot.entity.PersToPers;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.exception.ParseToJsonException;

import java.util.List;

import static ru.mgubin.tbot.service.Constants.DB_URL;

@Service
@ToString
@NoArgsConstructor
public class UserDB {
    /**
     * Заночит в базу контакта
     *
     * @param user сущность контакта
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public void createUser(User user) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(user.toJson(), headers);
            restTemplate.postForObject(DB_URL + "/persons", request, String.class);

        } catch (RuntimeException e) {
            throw new ParseToJsonException();
        }
    }

    /**
     * Поиск. Найти клиента по его гендорным предпочтениям
     *
     * @param userId id клиента
     * @return List<User> список клиентов
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public List<User> getUsersByGender(int userId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            List<User> response = restTemplate.getForObject(
                    DB_URL + "/persons_crush/" + userId,
                    List.class);
            return response;

        } catch (RuntimeException e) {
            throw new ParseToJsonException();
        }
    }

    /**
     * Поиск. Найти клиента по его гендорным предпочтениям
     *
     * @param userId  id клиента
     * @param crushId id любимца
     * @return List<User> список клиентов
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public Boolean isCrushLikeUser(int userId, int crushId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Boolean response = restTemplate.getForObject(
                    DB_URL + "/crushes/" + crushId + "/" + userId,
                    Boolean.class);
            return response;
        } catch (RuntimeException e) {
            throw new ParseToJsonException();
        }
    }

    /**
     * Создает связь для интерсект таблицы клиентов
     *
     * @param persToPers Сущность связей между клиентами
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public void makeLikeToUser(PersToPers persToPers) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(persToPers.toJson(), headers);
            restTemplate.postForObject(DB_URL + "/crushes", request, String.class);
        } catch (RuntimeException e) {
            throw new ParseToJsonException();
        }
    }

    /**
     * Убирает связь для интерсект таблицы клиентов
     *
     * @param persToPers Сущность связей между клиентами
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public void removeLikeToUser(PersToPers persToPers) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(persToPers.toJson(), headers);
            restTemplate.exchange(DB_URL + "/crushes", HttpMethod.DELETE, request, String.class);
        } catch (RuntimeException e) {
            throw new ParseToJsonException();
        }
    }

}
