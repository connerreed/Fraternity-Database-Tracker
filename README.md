# Fraternity Tracking Application

This application is designed to track information about a fraternity using a MySQL database and a Java GUI.


## Prerequisites

Before running this application, ensure you have the following prerequisites installed:

- Java Development Kit (JDK) 8 or above
- MySQL Server


## Installation and Setup

1. Clone the repository to your local machine: 

        - git clone https://github.com/connerreed/Fraternity-Database-Tracker.git

2. Create a MySQL database and import the necessary SQL files. Instructions can be found in the `sql/README.md` file.

3. Update the database connection details in the `src/com/yourname/fraternitytracker/DatabaseManager.java` file.

4. Compile the Java source code files: 

        - javac src/myfraternity/*.java -d bin/

5. Run the application:

        - java -cp bin/ myfraternity.Main


## Usage

1. Launch the application by executing the steps in the "Installation and Setup" section.

2. The GUI will appear, allowing you to perform various actions such as adding new members, updating information, and generating reports.

3. Use the buttons and input fields in the GUI to interact with the database and modify fraternity information.


## File Structure

- `lib/`: Contains the MySQL Connector JAR file for establishing the database connection.
- `sql/`: Contains SQL scripts for database setup, schema, and sample data.
- `src/`: Contains the Java source code files for the fraternity tracking application.
- `README.md`: This file provides an overview of the project.


## Contributing

If you encounter any issues, have suggestions for improvements, or would like to contribute, please create an issue or submit a pull request.


## License

This project is licensed under the [MIT License](LICENSE).


## Contact Information

For any inquiries or questions, please contact Conner Reed at connerdreed@gmail.com
