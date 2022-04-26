
# PlumbManager

This project allows a business to record their employees, customers, work orders, and addresses for those work orders. Work orders allow time stamped notes to be recorded by employees. Employees can be assigned to multiple work orders that they are conducting business on. This particular project happens to have a focus on the plumbing trade, but can be adapted to any other business model.



## Difficulties

One particular problem that set me back was using AJAX to dynamically load the contents of a page with information from the database. When seralizing the information to JSON, there were cirular references that ultimately ended up in an infinite loop, resulting in a stack overflow. I was able to exclude certain references from the JSON, but that solution would apply across the board, which I did not want. I found a solution with JSON views that allowed me to return only the bits I needed for that specific use case.

## Plans

If I had more time with the project, some additional items I would have liked to implement are:
* Removing and editing customer addresses
* Avatars for each employee
* Show a small icon of the avatar for assigned employees in a work order
* Filter and sort work orders

There are more ideas, but these were the pressing items I would have liked to implement next.

## Database Diagram
![](https://i.imgur.com/wjhJdYE.png)