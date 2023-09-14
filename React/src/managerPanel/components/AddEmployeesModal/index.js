import { Button, Form, Input, Modal } from "antd";
import { useEffect, useState } from "react";



const AddEmployeesModal = ({
  isModalOpen,
  onOk,
  onCancel,
  initialValues,

}) => {
  const [form] = Form.useForm();

  const onFinish = (values) => {
    //    console.log(values);
    onOk(values);
    form.resetFields();
  };

  useEffect(() => {
    if (form) {
      form.setFieldsValue(initialValues);
    }
  }, [form, initialValues]);

  // const [dateofemployment, setdateofemployment] = useState('');
  const [birthday, setbirthdate] = useState('');

  // const handledateofemployment = (e) => {
  //   setdateofemployment(e.target.value);
  // };

  const handlebirthdate = (e) => {
    setbirthdate(e.target.value);
  };



  return (
    <Modal
      title="Add Employee"
      open={isModalOpen}
      onCancel={onCancel}
      cancelButtonProps={{ style: { display: "none" } }}
      okButtonProps={{ style: { display: "none" } }}
    >
      <Form
        form={form}
        name="add-employee"
        onFinish={onFinish}
        wrapperCol={{ span: 16 }}
        labelCol={{ span: 8 }}
      >
        <Form.Item
          name="name"
          label="Employee Name"
          rules={[{ required: true }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="surname"
          label="Surname"
          rules={[{ required: true }]}
        >
          <Input />

        </Form.Item>
        <Form.Item
          name="birthday"
          label="Birthdate"
          rules={[{ required: true }]}
        >
          <Input
            type="date"
            value={birthday}
            onChange={handlebirthdate}
            style={{
              borderRadius: '5px',
              width: '315px',
              height: '35px',
              border: '1px solid #ccc',  
              borderColor: '#ccc',       
              borderWidth: '1px',        
              opacity: 0.7
            }}
            format="YYYY.MM.DD"
          />
        </Form.Item>
        <Form.Item
          name="birthdayPlace"
          label="Birth Place"
          rules={[{ required: true }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="email"
          label="Email"
          rules={[{ required: true }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="phone"
          label="Phone"
          rules={[{ required: true }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="address"
          label="Address"
          rules={[{ required: true }]}
        >
          <Input />
        </Form.Item >
        <Form.Item
          name="idNumber"
          label="Identification Number"
          rules={[{ required: true }]}
        >
          <Input />
        </Form.Item >
        {/* <Form.Item
          name="dateofemployment"
          label="Date Of Employment"
          rules={[{ required: true }]}>

          <Input
            type="date"
            value={dateofemployment}
            onChange={handledateofemployment}
            style={{
              borderRadius: '5px',
              width: '315px',
              height: '35px',
              border: '1px solid #ccc',  
              borderColor: '#ccc',       
              borderWidth: '1px',        
              opacity: 0.7
            }}
            format="YYYY.MM.DD"
          />

        </Form.Item> */}
        <Form.Item
          name="occupation"
          label="Occupation"
          rules={[{ required: true }]}>

          <Input />
        </Form.Item>
        {/* <Form.Item
          name="companyname"
          label="Company Name"
          rules={[{ required: true }]}>

          <Input />
        </Form.Item>
        <Form.Item
          name="taxno"
          label="Tax No"
          rules={[{ required: true }]}>

          <Input />
        </Form.Item> */}
        <Form.Item
          name="salary"
          label="Salary"
          rules={[{ required: true }]}>

          <Input />
        </Form.Item>

        <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
          <Button type="primary" htmlType="submit">
            Add Employees
          </Button>
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default AddEmployeesModal;
