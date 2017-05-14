import { GET_ORDERS, SUBMIT_ORDER, GET_TRACKING_ID_ORDER } from '../actions/types';

export default function(state= {orders: [], trackingId: '', orderInfo: {}}, action) {
    switch(action.type) {
        case GET_ORDERS:
            return { ...state, orders: action.payload.data.data };
        case SUBMIT_ORDER:
            return {...state, trackingId: action.payload.data.data };
        case GET_TRACKING_ID_ORDER:
            return { ...state, orderInfo: action.payload.data.data };
        default:
            return state;
    }
}
