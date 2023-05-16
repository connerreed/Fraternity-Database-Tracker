# MySQL Database Setup

To run the fraternity tracking application, you need to set up a MySQL database and import the required SQL files. Follow the steps below to get started:

1. Install MySQL: If you haven't already, install MySQL on your system by following the official documentation for your operating system.

2. Create a new database: Open a MySQL client (e.g., MySQL command line client, MySQL Workbench, phpMyAdmin) and create a new database. Choose a suitable name for your database (e.g., `fraternity_db`).

3. Import the SQL files: In the `sql/` directory of this repository, you will find several SQL files. Import them in the following order:

   - `create_tables.sql`: This file contains the SQL statements to create the necessary tables for the fraternity tracking application.

   - `sample_data.sql` (optional): This file contains sample data to populate the tables with some initial records. Importing this file is recommended for testing purposes.

   You can import these files using a MySQL client or the command line. For example, you can use the following command in the terminal:
   
      - mysql -u your_username -p your_database_name < sql/file_name.sql
      
           - Replace 'your_username' with your MySQL username, `your_database_name` with the name of your created database, and `file_name.sql` with the appropriate SQL file name.
      
4. Update the database connection details: In the `src/myfraternity/util/DBUtil.java` file of this repository, update the connection details to match your MySQL database. Modify the `url`, `username`, and `password` variables with your specific database information.

   - String url = "jdbc:mysql://localhost:3306/your_database_name";
   - String username = "your_username";
   - String password = "your_password";
      - Replace `your_database_name`, `your_username`, and `your_password` with your actual database details. 



Once you have completed these steps, you will have a MySQL database set up with the necessary tables and, optionally, populated with sample data. 

You can then proceed with the installation and setup of the fraternity tracking application as mentioned in the main `README.md` file.

If you encounter any issues during the database setup or have any questions, feel free to reach out for assistance.
