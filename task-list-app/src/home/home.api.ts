import Api from "../common/api"
import { Task } from "../common/types";

const getTaskList = async () => {
  const response = await Api.get<Task[]>('/list');
  return response.data;
}

export { getTaskList };