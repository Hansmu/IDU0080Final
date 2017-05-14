import { getRequest, postRequest } from '../../utils/request-utils';
import { GET_ORDERS, GET_TRACKING_ID_ORDER, SUBMIT_ORDER } from './types';

export function getWaitingOrders() {
    return {
        type: GET_ORDERS,
        payload: getRequest('order/all')
    };
}

export function submitOrder(orderId) {
    return {
        type: SUBMIT_ORDER,
        payload: getRequest(`order/${orderId}`)
    };
}

export function getOrderByTrackingId(trackingId) {
    return {
        type: GET_TRACKING_ID_ORDER,
        payload: postRequest('order/tracking', { trackingId })
    };
}
