import React, { useEffect, useState } from 'react'
import { navigate, RouteComponentProps } from '@reach/router';
import { Task } from '../common/types';
import { getTaskList } from './home.api';
import { Checkbox, IconButton, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography } from '@material-ui/core';
import { Delete, Edit } from '@material-ui/icons';
import './Home.css';
import { deleteTask } from '../details/details.api';

interface HomeProps extends RouteComponentProps{

}

const Home: React.FC<HomeProps> = ({}) => {

    const [tasks, setTasks] = useState<Task[] | null>(null)

    useEffect(() => {
        const fetchTaskList = async () => {
            const taskList = await getTaskList()
            setTasks(taskList)
            console.log(taskList)
        }
        fetchTaskList()
    }, [])

    const onDelete = async (event: React.MouseEvent<HTMLButtonElement>, taskId: number) => {
      event.stopPropagation();
      await deleteTask(taskId);
      const listAfterDelete = await getTaskList();
      setTasks(listAfterDelete);
    }

    const onEdit = (taskId: number) => navigate(`task/${taskId}`);

    return (<>
        <Typography variant="h1" className="home-title">Task List Application</Typography>
        <TableContainer>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Completed</TableCell>
              <TableCell>Tast Title</TableCell>
              <TableCell>Due Date Time</TableCell>
              <TableCell>Created Date</TableCell>
              <TableCell>Edit</TableCell>
              <TableCell>Delete</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {tasks && tasks.map(task => (
              <TableRow key={task.taskId}>
                <TableCell>
                    <Checkbox checked={task.completed} />
                </TableCell>
                <TableCell>
                  {task.title}
                </TableCell>
                <TableCell>
                  {task.dueDate}
                </TableCell>
                <TableCell>
                  {task.createdDate}
                </TableCell>
                <TableCell>
                    <IconButton onClick={(event) => onEdit(task.taskId)} aria-label="edit" color="primary">
                        <Edit/>
                    </IconButton>
                </TableCell>
                <TableCell>
                    <IconButton onClick={(event) => onDelete(event, task.taskId)} aria-label="delete" color="secondary">
                        <Delete/>
                    </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
        </TableContainer>
      </>);
}

export default Home