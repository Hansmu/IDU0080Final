import React from 'react';
import { Route, IndexRoute } from 'react-router';

import Header from './containers/header';
import Orders from './containers/orders';

export default (
    <Route path="/" component={Header}>
        <IndexRoute component={Orders}/>
    </Route>
);
