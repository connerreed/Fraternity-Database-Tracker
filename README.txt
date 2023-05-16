1. Import SQL files to MySQL
2. Use the class DBUtil (found under "org.myfraternity.util.DBUtil") and change the username and password to connect to your MySQL database as needed for your system.
3. In order to execute this application, on the command line get inside the FraternityDatabase directory.
	Then, run this command for Windows: "start.bat" or run this for Mac/Linux: "start.sh". The program will then start.
4. Once Main is running, a window will open with a set of buttons, one for each action. 
5. The top section of the main window has a selection of entities to manage. Clicking one will open a new window, which is the management screen of that entity.
	The bottom section of the main window has a selection of views you can see. Clicking one will open a new window, which will show the view you selected.
	All windows besides the main window are safe to close. Closing main window will result in program termination.
6. Inside any of the entity management windows, you are shown a table of all data stored for that entity.
	There are 3 operations you can perform: ADD, DELETE, and UPDATE
	ADD: To add, simply click the "Add" button. This will pull up a data entry form.
		Any fields that take a Date as input must be formatted as "YYYY-MM-DD"
		Any fields that have a dropdown box are where you must select the entity in order to create the required relation between them.
			For example, in the Member creation, you must select which chapter that member belongs to.
		Once you are done filling out all fields, click the "Add" button at the bottom of the screen.
			The current data entry form will then close, and changes are reflected upon the view section of the Entity management screen of that entity you just added.
	DELETE: To delete a row, simply click the row you want to delete in the table, and then click the "Delete" button on the screen.
		After you click "Delete", the table should update with that row removed.
	UPDATE: To update a row, simply double-click an attribute to enter edit-mode with that attribute.
		Once you are done updating that attribute, press "enter" on the keyboard and the changes will reflect in the database and on the screen.

		
