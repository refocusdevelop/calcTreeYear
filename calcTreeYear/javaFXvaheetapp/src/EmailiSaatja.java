// THIS WILL BE DELETED


import java.util.HashMap;

public class EmailiSaatja {

    public void saada(Integer user_id, String kuupaev, String soe, String kylm) throws Exception {
        // Kuna oma meiliserverit üles seada on päris keerukas, siis kasutame veebimajutuse oma
        // Saame kasutaja andmed

        /*Puunaidud naidud = new Puunaidud();
        HashMap<String, String> andmed;
        andmed = naidud.saaKasutajaAndmed(user_id);

        String username = andmed.get("username");
        String name = andmed.get("name");
        String address = andmed.get("address");
        String saajaemail = andmed.get("saajaemail");

//  Paneme sõnumi kokku
        String message = String.format("Tere!\nAadressi %s veenäidud seisuga %s:\nSoe: %s\nKülm: %s\nTervitades,\n%s",
                address.trim(), kuupaev.trim(), soe, kylm, name.trim());

//  SimpleMail'iga e-maili saatmine
        Email email = new Email();

        email.setFromAddress(name, username);
        email.addRecipient(saajaemail, saajaemail, Message.RecipientType.TO);
        email.setSubject(address + " veenäidud");
        email.setText(message);

        try {
//  Meiliserveri ülesseadmine ja meili saatmine
            new Mailer("mail.veebimajutus.ee", 465, "veenaidud@bordeaux.ee", "EwGwPp7", TransportStrategy.SMTP_SSL).sendMail(email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        */
    }
}
