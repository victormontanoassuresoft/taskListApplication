import React from 'react'
import { RouteComponentProps } from '@reach/router';

interface TimezoneProps extends RouteComponentProps {
}

const Timezone: React.FC<TimezoneProps> = () => {
    
    return (
        <a href="https://timezonedb.com/">Time zone</a>
    );
}

export default Timezone