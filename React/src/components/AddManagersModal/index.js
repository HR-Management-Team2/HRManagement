import { Button, Form, Input, Modal, Select } from "antd";
import { useEffect } from "react";



const AddManagersModal = ({
  isModalOpen,
  onOk,
  onCancel,
  initialValues,
  managers,
  onStatusChange,
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

  // const config = {
  //   rules: [{ type: 'object', required: true, message: 'Please select time!' }],
  // };

  // MODALLAR AÇILAN FORMU DEĞİŞTİTİRYOR!!!!!!!!!!!
  const { Option } = Select;
  return (
    <Modal
      title="Add Manager"
      open={isModalOpen}
      onCancel={onCancel}
      cancelButtonProps={{ style: { display: "none" } }}
      okButtonProps={{ style: { display: "none" } }}
    >
      <Form
        form={form}
        name="add-manager"
        onFinish={onFinish}
        wrapperCol={{ span: 16 }}
        labelCol={{ span: 8 }}
      >
        <Form.Item
          name="authId"
          label="authId"
          rules={[{ required: true }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="name"
          label="Name"
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
          name="email"
          label="Email"
          rules={[{ required: true }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="companyName"
          label="Company Name"
          rules={[{ required: true }]}
        >
          <Input />
        </Form.Item >
        <Form.Item
          name="taxNo"
          label="Tax Number"
          rules={[{ required: true }]}
        >
          <Input />
        </Form.Item >
        <Form.Item
          name="status"
          label="Status"
          rules={[{ required: true }]}
        >
          <Select
            defaultValue={managers.status} 
            onChange={(value) => onStatusChange(managers.id, value)} 
            style={{ width: 100 }}
          >
            <Option value="ACTIVE">Active</Option>
            <Option value="PENDING">Passive</Option>
          </Select>
        </Form.Item >

        <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
          <Button type="primary" htmlType="submit">
            Save
          </Button>
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default AddManagersModal;
