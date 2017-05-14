import React, { Component } from 'react';

export default class Header extends Component {

  renderNavbar() {
    return (
        <nav className="navbar navbar-default">
          <div className="container-fluid">
            <div className="navbar-header">
              <a className="navbar-brand" href="#">IDU0080 Final Homework</a>
            </div>
          </div>
        </nav>
    );
  }


  render() {
    return (
        <div>
          { this.renderNavbar() }
          { this.props.children }
        </div>
    );
  }
}
