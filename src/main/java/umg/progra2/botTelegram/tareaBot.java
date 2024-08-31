package umg.progra2.botTelegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class tareaBot extends TelegramLongPollingBot {

    // Información personal
    private final String nombre = "Gustavo Barrera";
    private final String carnet = "0905-19-9068";
    private final String semestre = "4to Semestre";

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
        // Verifica si la actualización contiene un mensaje y si ese mensaje tiene texto.
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            // Comando /info
            if (message_text.equalsIgnoreCase("/info")) {
                String info = "Información personal:\n" +
                        "Nombre: " + nombre + "\n" +
                        "Carnet: " + carnet + "\n" +
                        "Semestre: " + semestre;
                sendText(chat_id, info);

                // Comando /progra
            } else if (message_text.equalsIgnoreCase("/progra")) {
                sendText(chat_id, "Me parece una clase muy buena y con la que voy a comer de grande");

                // Comando /hola
            } else if (message_text.equalsIgnoreCase("/hola")) {
                String userName = update.getMessage().getFrom().getFirstName();
                String fechaActual = new SimpleDateFormat("EEEE dd 'de' MMMM").format(new Date());
                sendText(chat_id, "hola, " + userName + ", hoy es " + fechaActual);

                // Comando /cambio
            } else if (message_text.startsWith("/cambio")) {
                try {

                    String[] partes = message_text.split(" ");
                    double euros = Double.parseDouble(partes[1]);

                    double tipoDeCambio = 8.9;


                    double quetzales = euros * tipoDeCambio;
                    sendText(chat_id, euros + " euros son " + String.format("%.2f", quetzales) + " quetzales.");
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    sendText(chat_id, "Por favor, usa el formato /cambio [cantidad_en_euros]. Ejemplo: /cambio 897");
                }
            } else {
                sendText(chat_id, "Comando no reconocido. Por favor, usa uno de los comandos válidos como /info, /progra, /hola o /cambio.");
            }
        }
    }


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
