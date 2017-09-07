# MVP-TaskCreation

A simple task creation app which let's you create task , edit task , delete task and list all the tasks. 

MVP architecture is followed in this app. 

# Save task logic : 

Whenever `save task` button is clicked , view (here in project it refers to TaskListFragment) talks with presenter which in turn
communicates with repoistory and saves the task content in db using `Realm`. After this repoistory let's presenter know about the 
save task status which notifies view which updates the recycler view with whatever data received from repoistory via presenter. 

# Delete task logic : 

View communicates with presenter(with the id of task to be deleted) to delete the task which in turn communitcates with repoistory. And same as above ^ view is notified
about the latest data available in db which is used for updating the recycler view. 

# Update task logic : 
same as above 

# List all the tasks :
same as above 



 
