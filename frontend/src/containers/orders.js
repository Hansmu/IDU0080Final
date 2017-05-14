import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import { Table, Button, FormControl, Row, Col } from 'react-bootstrap';

import { getWaitingOrders, getOrderByTrackingId, submitOrder } from '../actions';

const centered = { textAlign: 'center' };

class Orders extends Component {

    constructor(props) {
        super(props);

        this.state = {
            trackingId: ''
        };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.createOrdersTable = this.createOrdersTable.bind(this);
        this.createListOfOrders = this.createListOfOrders.bind(this);
    }

    componentWillMount() {
        this.props.dispatch(getWaitingOrders());
    }

    createOrdersTable() {
        return (
            <Table striped responsive condensed hover>
                <thead>
                    <tr>
                        <th></th>
                        <th style={centered}>
                            Order Cost
                        </th>
                        <th style={centered}>
                            Order Delivery Address
                        </th>
                    </tr>
                </thead>
                <tbody>
                    {this.createListOfOrders()}
                </tbody>
            </Table>
        );
    }

    createListOfOrders() {
        return this.props.orders.map(order => {
            return (
                <tr key={order.id}>
                    <td>
                        <Button bsStyle="success" onClick={() => this.props.dispatch(submitOrder(order.id))}>
                            Submit
                        </Button>
                    </td>
                    <td>
                        { order.orderCost }
                    </td>
                    <td>
                        { order.deliveryAddress }
                    </td>
                </tr>
            );
        });
    }

    handleSubmit(event) {
        event.preventDefault();
        this.props.dispatch(getOrderByTrackingId(this.state.trackingId));
    }

    render () {
        return (
            <div style={centered}>
                { this.props.trackingNumber &&
                    <div style={{marginTop: '20px'}}>
                        <h3>Your tracking ID is: </h3>
                        <h1>{this.props.trackingNumber}</h1>
                    </div>
                }

                <h2 style={{marginTop: '65px', marginBottom: '20px'}}>Unsubmitted orders</h2>
                <Row>
                    <Col md={2}/>
                    <Col md={8}>
                        { this.createOrdersTable() }
                    </Col>
                    <Col md={2}/>
                </Row>

                <h2 style={{marginTop: '65px', marginBottom: '60px'}}>Enter your tracking ID to see your order</h2>
                <form onSubmit={this.handleSubmit}>
                    <Row>
                        <Col md={3}/>
                        <Col md={6}>
                            <FormControl
                                style={centered}
                                name="trackingId"
                                type="text"
                                value={this.state.trackingId}
                                placeholder="Enter your tracking ID"
                                onChange={event => this.setState({trackingId: event.target.value})}/>
                        </Col>
                        <Col md={3}/>
                    </Row>
                </form>

                { this.props.orderInfo  && this.props.orderInfo.deliveryDate &&
                    <div style={{marginTop: '65px'}}>
                        <h2 style={{marginTop: '20px', marginBottom: '20px'}}>Your order information</h2>
                        <div>
                            <h5>Tracking number: {this.props.orderInfo.trackingNumber}</h5>
                            <h5>Order price: {this.props.orderInfo.orderPrice}</h5>
                            <h5>Order delivery date: {this.props.orderInfo.deliveryDate}</h5>
                        </div>
                    </div>
                }
            </div>
        );
    }
}

Orders.propTypes = {
    orders: PropTypes.array,
    dispatch: PropTypes.func
};

const mapStateToProps = (state) => ({
    orders: state.main.orders,
    trackingNumber: state.main.trackingId,
    orderInfo: state.main.orderInfo
});

export default connect(mapStateToProps, dispatch => ({dispatch}))(Orders);
