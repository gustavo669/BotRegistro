package umg.progra2.botTelegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "@Gusbarrera_bot";
    }

    @Override
    public String getBotToken() {
        return "7321756387:AAHyayAj3YoX5w9GdzhrvEZ9cMRCvHPuRlU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Obtener información de la persona que manda los mensajes
        String nombre = update.getMessage().getFrom().getFirstName();
        String apellido = update.getMessage().getFrom().getLastName();
        String nickName = update.getMessage().getFrom().getUserName();

        // Verifica si la actualización contiene un mensaje y si ese mensaje tiene texto.
        if (update.hasMessage() && update.getMessage().hasText()) {
            System.out.println("Hola " + nickName + ". Tu nombre es: " + nombre + " y tu apellido es: " + apellido);
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            // Responde al mensaje "Hola"
            if (message_text.equalsIgnoreCase("Hola")) {
                sendText(chat_id, "Hola " + nombre + ", Gusto Saludarte");
            } else {
                sendText(chat_id, "No entendí tu mensaje, pero ¡hola de todos modos!");
            }
        }
    }

    // Método para enviar un mensaje de texto al chat
    private void sendText(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}


