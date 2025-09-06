import java.util.List;

public enum Form {

    EMAIL ("Student Email"),
    NAME ("Student Name"),
    GENDER("Gender"),
    BIRTH("Date of Birth"),
    MOBILE("Mobile"),
    SUBJECTS("Subjects"),
    HOBBIES("Hobbies"),
    ADDRESS("Address"),
    PICTURE("Picture"),
    STATE("State and City");

    private String label;

    public static Form fromLabel(String label) {
        for (Form form : values()) {
            if (form.getLabel().equals(label)) {
                return form;
            }
        }
        // Если не нашли подходящий элемент
        return null;
    }

    Form(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
