import Api from "../common/api";
import { Task } from "../common/types";

const getTask = async(taskId: number): Promise<Task> => {
  const { data } = await Api.get<Task>(`/${taskId}`);
  return data;
}

const updateTask = async (task: Task): Promise<Task> => {
  const { data } = await Api.patch<Task, Task>(`/${task.taskId}`, task);
  return data
}

const createTask = async (task: Task): Promise<Task> => {
  const { data } = await Api.post<Task, Task>(`/create`, task);
  return data;
}

const deleteTask = async (taskId: number) => {
  const { data } = await Api.delete<Task[]>(`/${taskId}`);
  return data
}

export {
    getTask,
    updateTask,
    createTask,
    deleteTask
}
