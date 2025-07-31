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

    Form(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
