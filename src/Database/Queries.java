package Database;

public class Queries {
    private String createDoctorsTable = "CREATE TABLE doctors (" +
            " id SERIAL PRIMARY KEY," +
            " specialties_id INTEGER REFERENCES specialties(id)," +
            " name VARCHAR(40) NOT NULL," +
            " telephone NUMERIC(45) NOT NULL);";

    private String createExertsTable = "CREATE TABLE exerts (" +
            " id SERIAL PRIMARY KEY," +
            " doctors_id INTEGER REFERENCES doctors(id)," +
            " specialties_id INTEGER REFERENCES specialties(id));";

    private String createPatientsTable = "CREATE TYPE genre AS ENUM ('Female', 'Male');" +
            " CREATE TABLE patients (" +
            " id SERIAL PRIMARY KEY," +
            " name VARCHAR(40) not null," +
            " telephone NUMERIC(45) NOT NULL," +
            " document VARCHAR(45) NOT NULL," +
            " address VARCHAR(255) NOT NULL," +
            " age INTEGER NOT NULL," +
            " genre genre NOT NULL);";

    private String createAgendaTable = "CREATE TYPE days_of_week AS ENUM ('Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat');\n" +
            " CREATE TABLE agenda (" +
            " id SERIAL PRIMARY KEY," +
            " doctors_id INTEGER REFERENCES doctors(id)," +
            " day_of_week days_of_week," +
            " start TIME NOT NULL," +
            " end TIME NOT NULL);";

    private String createDiagnosisTable = "CREATE TABLE diagnosis (" +
            " id SERIAL PRIMARY KEY," +
            " diseases_id INTEGER REFERENCES diseases(id)," +
            " consultation_id INTEGER REFERENCES consultation(id)," +
            " Treatment VARCHAR(255) NOT NULL," +
            " remedies VARCHAR(255) NOT NULL," +
            " observation VARCHAR(255) NOT NULL);";

    private String createDiseasesTable = "CREATE TABLE diseases (" +
            " id SERIAL PRIMARY KEY," +
            " specialties_id INTEGER REFERENCES specialties(id)," +
            " name VARCHAR(45) NOT NULL);";

    private String createSpecialtyTable = "CREATE TABLE specialties (" +
            " id SERIAL PRIMARY KEY," +
            " index INTEGER NOT NULL," +
            " name VARCHAR(45) NOT NULL);";

    private String createTaxesTable = "CREATE TABLE taxes (" +
            " id SERIAL PRIMARY KEY," +
            " specialties_id INTEGER REFERENCES specialties(id)," +
            " date TIMESTAMP NOT NULL," +
            " value NUMERIC(20, 2) NOT NULL);";

    private String createConsultationTable = "CREATE TYPE payment_method AS ENUM ('Credit card', 'Debit card', 'Money');\n" +
            " CREATE TABLE consultation (" +
            " id  SERIAL PRIMARY KEY," +
            " patients_id INTEGER REFERENCES patients(id)," +
            " doctors_id INTEGER REFERENCES doctors(id)," +
            " specialties_id INTEGER REFERENCES specialties(id)," +
            " date DATE NOT NULL," +
            " start_at TIME NOT NULL," +
            " end_at TIME NOT NULL," +
            " paid BOOLEAN NOT NULL DEFAULT false," +
            " amount_paid NUMERIC(20, 2) DEFAULT NULL," +
            " payment_method payment_method DEFAULT NULL);";

    private String insertIntoDefaultSpecialties = String.join(
            "\n",
            "",
            "INSERT INTO specialties (index, name) VALUES(1, 'cardiac') ",
            "INSERT INTO specialties (index, name) VALUES(2, 'dental') ",
            "INSERT INTO specialties (index, name) VALUES(3, 'orthopedic') ",
            "INSERT INTO specialties (index, name) VALUES(4, 'dermatology') ",
            "INSERT INTO specialties (index, name) VALUES(5, 'psychiatrist') ",
            "INSERT INTO specialties (index, name) VALUES(6, 'urology') "
    );

    private String insertIntoDefaultTaxes = String.join(
            "\n",
            "",
            "INSERT INTO taxes (specialties_id, month, year, value) VALUES(1, 'now', 20.21) ",
            "INSERT INTO taxes (specialties_id, month, year, value) VALUES(2, 'now', 11.3) ",
            "INSERT INTO taxes (specialties_id, month, year, value) VALUES(3, 'now', 10.6) ",
            "INSERT INTO taxes (specialties_id, month, year, value) VALUES(4, 'now', 4.5) ",
            "INSERT INTO taxes (specialties_id, month, year, value) VALUES(5, 'now', 3.22) ",
            "INSERT INTO taxes (specialties_id, month, year, value) VALUES(6, 'now', 6.1) "
    );

    private String insertIntoDefaultDiseases = String.join(
            "\n",
            "",
            "INSERT INTO specialties (specialties_id, name) VALUES(1, 'Stroke') ",
            "INSERT INTO specialties (specialties_id, name) VALUES(1, 'Arrhythmia') ",
            "INSERT INTO specialties (specialties_id, name) VALUES(2, 'Sensitive Teeth') ",
            "INSERT INTO specialties (specialties_id, name) VALUES(2, 'Gum') ",
            "INSERT INTO specialties (specialties_id, name) VALUES(3, 'Arthritis') ",
            "INSERT INTO specialties (specialties_id, name) VALUES(3, 'Fractures') ",
            "INSERT INTO specialties (specialties_id, name) VALUES(4, 'Acne') ",
            "INSERT INTO specialties (specialties_id, name) VALUES(4, 'Chicken Pox') ",
            "INSERT INTO specialties (specialties_id, name) VALUES(5, 'Depression') ",
            "INSERT INTO specialties (specialties_id, name) VALUES(5, 'Eating disorders') ",
            "INSERT INTO specialties (specialties_id, name) VALUES(6, 'Urinary Incontinence') ",
            "INSERT INTO specialties (specialties_id, name) VALUES(6, 'prostate cancer') "
    );

    public String initQuery() {
        String query = String.join(
                "\n",
                createSpecialtyTable,
                createDiseasesTable,
                createDoctorsTable,
                createPatientsTable,
                createConsultationTable,
                createDiagnosisTable,
                createAgendaTable,
                createExertsTable,
                createTaxesTable
        );

        return query;
    }

    public String insertQuery() {
        String query = String.join(
                "\n",
                insertIntoDefaultSpecialties,
                insertIntoDefaultTaxes,
                insertIntoDefaultDiseases
        );

        return query;
    }
}
