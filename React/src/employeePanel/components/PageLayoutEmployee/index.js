import { Button, Col, Row, Input } from "antd";
import React from "react";
import "./index.scss";

const PageLayout = ({ children, buttons, onSearch }) => {
  const { Search } = Input;

  const handleSearch = (value) => {
    if (onSearch) {
      onSearch(value);
    }
  };

  return (
    <Row className="page-layout">
      <Col>
        <Row>
          <Col flex="auto">
            { onSearch &&
              <Search
              placeholder="Search..."
              onSearch={handleSearch}
              enterButton
              style={{ width: 400 }}
            />
            }
            
          </Col>
          <Col>
            {buttons.map((button) => {
              const { key, text, ...otherProps } = button;
              return (
                <Button key={key} {...otherProps}>
                  {text}
                </Button>
              );
            })}
          </Col>
        </Row>
      </Col>
      <Col>{children}</Col>
    </Row>
  );
};

export default PageLayout;
