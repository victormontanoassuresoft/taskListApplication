import React, { FormEventHandler, useEffect, useState } from 'react'
import { navigate, RouteComponentProps } from '@reach/router';
import { Box, Button, makeStyles, TextField } from '@material-ui/core';
import { Timezone } from '../common/timezone';
import { getTimezone } from './timezone.api';

interface TimezoneProps extends RouteComponentProps {
  coordinate?: string;
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

const TimezonePage: React.FC<TimezoneProps> = ({coordinate}) => {

    const classes = useStyles();

    const [error, setError] = useState<string | null>(null);
    const [timezone, setTimezone] = useState<Timezone | null>(null);

    useEffect(() => {
        if(!coordinate) {
          return;
        }
        getTimezone(coordinate).then(timezone => setTimezone(timezone)).catch(err => {
            setError(err.response.data.error)
        })
    
    }, [coordinate])

    const onFormSubmit: FormEventHandler<HTMLFormElement> = async (e) => {
        e.preventDefault();
        navigate(`/timezone/${coordinate}`);

    }

    const onFormValueChange = (e: React.ChangeEvent<HTMLInputElement>) => {
      coordinate = e.target.value;
  }
    
    return (
        <div>
            <a href="https://timezonedb.com/">Time zone</a>
            <form onSubmit={onFormSubmit}>
                <Box display="flex" alignItems="center" justifyContent="center" flexDirection="column">
                    <TextField onChange={onFormValueChange} className={classes.spacing} name="coordinate" label="Coordinates" placeholder="-17.414,-66.1653"/>
                    <Button className={classes.spacing} type="submit" variant="contained" color="primary">Get time zone</Button>
                </Box>
            </form>
            <Box display="flex" alignItems="center" justifyContent="center" flexDirection="column">
                <TextField className={classes.spacing} name="zoneName" value={timezone?.zoneName || ''} label="Zone Name" placeholder="Zone Name"/>
                <TextField className={classes.spacing} name="abbreviation" value={timezone?.abbreviation || ''} label="Abbreviation" placeholder="Abbreviation"/>
                <TextField className={classes.spacing} name="gmtOffset" value={timezone?.gmtOffset || ''} label="GMT Offset" placeholder="GMT Offset"/>
                <TextField className={classes.spacing} name="dst" value={timezone?.dst || ''} label="Daylight Saving time" placeholder="Daylight Saving time"/>
                <TextField className={classes.spacing} name="formatted" value={timezone?.formatted || ''} label="Current time" placeholder="Current time"/> 
            </Box>
        </div>
    );
}

export default TimezonePage