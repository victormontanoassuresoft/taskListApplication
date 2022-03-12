import React, { FormEventHandler, useEffect, useState } from 'react'
import { RouteComponentProps, useNavigate } from '@reach/router';
import { Task } from '../common/types';
import { createTask, deleteTask, getTask, updateTask } from './details.api';
import { Box, Button, Checkbox, makeStyles, TextField, Typography } from '@material-ui/core';

interface DetailsProps extends RouteComponentProps {
    taskId?: number;
}

const useStyles = makeStyles({
    spacing: {
      marginBottom: '16px'
    },
    error: {
      color: 'red',
      fontSize: '20px',
      fontWeight: 'bold'
    }
})

const Details: React.FC<DetailsProps> = ({taskId}) => {

    const classes = useStyles();
    const navigate = useNavigate();

    const [error, setError] = useState<string | null>(null);
    const [task, setTask] = useState<Task | null>(null);

    useEffect(() => {
        if(!taskId) {
          return;
        }
        getTask(taskId).then(task => setTask(task)).catch(err => {
            setError(err.response.data.error)
        })
    
    }, [taskId])

    const onFormSubmit: FormEventHandler<HTMLFormElement> = async (e) => {
        e.preventDefault();
        if(!task) {
          return;
        }
        // check whether to update or create new
        if(taskId) {
          task.taskId = taskId;
          await updateTask(task)
      
        } else {
          await createTask(task) 
        }
        navigate('/')
    }

    const onDelete = async () => {
        if(!taskId) {
          return;
        }
        await deleteTask(taskId);
        navigate('/')
    }

    const onFormValueChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setTask(prevValue => {
          if(prevValue) {
            return {
              ...prevValue,
              [e.target.name]: e.target.value
            } as Task
          } else {
            return {
              [e.target.name]: e.target.value
            } as unknown as Task
          }
        })
        console.log(e.target.value)
        console.log(e.target.name)
    }

    return (<div>
        <Typography variant="h1">{task ? task.title : 'Add new Task'}</Typography>
        <form onSubmit={onFormSubmit}>
          <Box display="flex" alignItems="center" justifyContent="center" flexDirection="column">
            <TextField onChange={onFormValueChange} className={classes.spacing} name="title" value={task?.title || ''} label="Title" placeholder="Title"/>
            <TextField
              id="dueDate"
              label="Due Date"
              name="dueDate"
              type="date"
              onChange={onFormValueChange}
              InputLabelProps={{
                shrink: true,
              }}
            />
            <p>Completed</p>
            <Checkbox onChange={onFormValueChange} className={classes.spacing} name="completed" checked={task?.completed || false}/>
            <Button className={classes.spacing} type="submit" variant="contained" color="primary">Submit</Button>
            <Button disabled={!taskId} variant="outlined" color="secondary" onClick={onDelete}>Delete</Button>
          </Box>
        </form>
        {error && <p className={classes.error}>{error}</p>}
    </div>);
}

export default Details