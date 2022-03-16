import Api from "../common/api";
import { Coordinate } from "../common/coordenate";
import { Timezone } from "../common/timezone";

const getTimezone = async(coordinate: Coordinate): Promise<Timezone> => {
  const { data } = await Api.getTimezone<Timezone, Coordinate>(`/timezone`, coordinate);
  return data;
}

export {
    getTimezone
}