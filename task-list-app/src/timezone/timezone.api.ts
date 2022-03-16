import Api from "../common/api";
import { Timezone } from "../common/timezone";

const getTimezone = async(coordinate: string): Promise<Timezone> => {
  const { data } = await Api.get<Timezone>(`/timezone/${coordinate}`);
  return data;
}

export {
    getTimezone
}