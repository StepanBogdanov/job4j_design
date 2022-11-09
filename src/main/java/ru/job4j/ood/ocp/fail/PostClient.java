package ru.job4j.ood.ocp.fail;

public class PostClient {

    private Logger logger;

    public PostClient(Logger logger) {
        this.logger = logger;
    }

    public void send(String message) {
        logger.log(String.format("Отправлено сообщение: %s", message));
    }
    /*
    Если мы захотим расширить функционал логера, например сохранять сообщения в БД, изменять придется и класс, который
    использует этот логер.
     */
}

class Logger {
    public void log(String logText) {
        /*
        Сохраняем лог в файл
        */
    }
}
