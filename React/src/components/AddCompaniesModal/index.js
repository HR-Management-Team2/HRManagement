import { Button, Form, Input, Modal } from "antd";
import { useEffect, useState } from "react";


const AddCompaniesModal = ({
  isModalOpen,
  onOk,
  onCancel,
  initialValues,
}) => {
  const [form] = Form.useForm();


  const onFinish = (values) => {
    console.log(values);
    onOk(values);
    form.resetFields();
  };

  useEffect(() => {
    if (form) {
      form.setFieldsValue(initialValues);
    }
  }, [form, initialValues]);

  const [inputValue, setInputValue] = useState('');
  const handleChange = (event) => {
    setInputValue(event.target.value);
  };


  return (
    <Modal
      title="Add Company"
      open={isModalOpen}
      onCancel={onCancel}
      cancelButtonProps={{ style: { display: "none" } }}
      okButtonProps={{ style: { display: "none" } }}
    >
      <Form
        form={form}
        name="add-company"
        onFinish={onFinish}
        wrapperCol={{ span: 16 }}
        labelCol={{ span: 8 }}
      >
        <Form.Item
          name="name"
          label="Company Name"
          rules={[{ required: true }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="taxNumber"
          label="Tax Number"
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
          name="yearOfEstablishment"
          label="Establish"
          rules={[{ required: true }]}
        >
          <Input 
            type="date"
            value={inputValue}
            onChange={handleChange}
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


        <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
          <Button type="primary" htmlType="submit">
            Add Companies
          </Button>
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default AddCompaniesModal;
