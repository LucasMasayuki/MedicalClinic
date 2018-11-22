public class Queries {
    private String createDoctorsTable = "CREATE TABLE doctors (" +
            " id SERIAL PRIMARY KEY," +
            " specialty_id INTEGER REFERENCES specialty(id)," +
            " name VARCHAR(40) NOT NULL," +
            " telephone NUMERIC(45) NOT NULL);";

    private String createExertsTable = "CREATE TABLE exerts (" +
            " id SERIAL PRIMARY KEY," +
            " doctors_id INTEGER REFERENCES doctors(id)," +
            " specialty_id INTEGER REFERENCES specialty(id));";

    private String createPatientsTable = "CREATE TYPE sex AS ENUM ('Female', 'Male');" +
            " CREATE TABLE patients (" +
            " id SERIAL PRIMARY KEY," +
            " name VARCHAR(40) not null," +
            " telephone NUMERIC(45) NOT NULL," +
            " document VARCHAR(45) NOT NULL," +
            " address VARCHAR(255) NOT NULL," +
            " age INTEGER NOT NULL," +
            " sex sex NOT NULL);";

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
            " name VARCHAR(45) NOT NULL);";

    private String createSpecialtyTable = "CREATE TABLE specialty (" +
            " id SERIAL PRIMARY KEY," +
            " index INTEGER NOT NULL," +
            " name VARCHAR(45) NOT NULL);";

    private String createTaxesTable = "CREATE TABLE taxes (" +
            " id SERIAL PRIMARY KEY," +
            " specialty_id INTEGER REFERENCES specialty(id)," +
            " month TIME NOT NULL," +
            " year TIME NOT NULL," +
            " value NUMERIC(20, 2) NOT NULL);";

    private String createConsultationTable = "CREATE TYPE payment_method AS ENUM ('Credit card', 'Debit card', 'Money');\n" +
            " CREATE TABLE consultation (" +
            " id  SERIAL PRIMARY KEY," +
            " patients_id INTEGER REFERENCES patients(id)," +
            " doctors_id INTEGER REFERENCES doctors(id)," +
            " specialty_id INTEGER REFERENCES specialty(id)," +
            " date DATE NOT NULL," +
            " start_at TIME NOT NULL," +
            " end_at TIME NOT NULL," +
            " paid BOOLEAN NOT NULL DEFAULT false," +
            " amount_paid NUMERIC(20, 2) DEFAULT NULL," +
            " payment_method payment_method DEFAULT NULL);";

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
}
