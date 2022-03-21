import React, { useEffect, useState } from 'react'
import { navigate, RouteComponentProps } from '@reach/router';
import { Task } from '../common/types';
import { getTaskList } from './home.api';
import { Checkbox, Fab, IconButton, makeStyles, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography } from '@material-ui/core';
import { Add, Delete, Edit } from '@material-ui/icons';
import './Home.css';
import { deleteTask, updateCompletedTask } from '../details/details.api';
import { connect } from 'react-redux';

const useStyles = makeStyles({
  tableRow: {
    cursor: 'pointer'
  },
  fab: {
    position: 'fixed',
    bottom: '32px',
    right: '32px'
  }
})

interface HomeProps extends RouteComponentProps{

}

const Home: React.FC<HomeProps> = ({}) => {

  const classes = useStyles();
  const [tasks, setTasks] = useState<Task[] | null>(null)

    useEffect(() => {
        const fetchTaskList = async () => {
            this.props.dispatch({type: 'GETLIST'});
        }
        fetchTaskList()
    }, [])

    const onDelete = async (event: React.MouseEvent<HTMLButtonElement>, taskId: number) => {
      event.stopPropagation();
      await deleteTask(taskId);
      this.props.dispatch({type: 'GETLIST'});
    }

    const onCompleted = async (event: React.MouseEvent<HTMLButtonElement>, taskId: number) => {
      event.stopPropagation();
      await updateCompletedTask(taskId);
      this.props.dispatch({type: 'GETLIST'});
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
            {this.props.tasks && this.props.tasks.map(task => (
              <TableRow key={task.taskId}>
                <TableCell>
                    <IconButton onClick={(event) => onCompleted(event, task.taskId)} aria-label="edit" color="primary">
                      <Checkbox checked={task.completed}/>
                    </IconButton>
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
                    <IconButton onClick={() => onEdit(task.taskId)} aria-label="edit" color="primary">
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
        <Fab onClick={() => navigate('/task/create')} className={classes.fab} color="primary" aria-label="add">
          <Add />
        </Fab>
      </>);
}

const mapStateToProps = (state) => ({
  tasks: state.tasks
})
export default connect(mapStateToProps)(Home);