
import { useEffect, useState } from "react";
import PageLayoutManager from "./PageLayoutManager";
import { Button, Table } from "antd";
import axios from "axios";
import { EditOutlined, DeleteOutlined  } from "@ant-design/icons";
import AddManagersModal from "./AddManagersModal";
import dayjs from 'dayjs';


const Managerlist = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [managers, setManagers] = useState([]);
  const [initialValues, setInitialValues] = useState([]);
  const [search, setSearch] = useState("");

  const buttons = [
    {
      key: "addManagers",
      text: "Add Manager",
      type: "primary",
      onClick: () => {
        setIsModalOpen(true);
        setInitialValues(null);
      },
    },
  ];

  const onOkAddModel = (values) => {
    setIsModalOpen(false);
    console.log(values);

    if (initialValues) {
      axios
        .put(`http://34.155.188.71/user/update-manager/${initialValues.authId}`, {
          ...values
        })
        .then((res) => {
          setManagers((prevState) => {
            return prevState.map((u) => {
              if (res.data.authId === u.authId) {
                return {
                  ...res.data,
                };
              } 
              return u;
            });
          });
        });
    } else {
      const newManagers = {
        name: values.name,
        surname: values.surname,
        email: values.email,
        companyname: values.companyName,
        taxno: values.taxNo,
        status: values.status
      };
      axios.post("http://localhost:5000/managers", newManagers).then((res) => {
        setManagers((prevState) => [
          ...prevState,
          {
            ...res.data, 
          },
        ]);
      });
    }
  };

  const onCancelAddModel = () => {
    setIsModalOpen(false);
  };

  const onClickEdit = (row) => {
    setIsModalOpen(true);
    row.establish=dayjs(row.establish)
    setInitialValues(row);
  };
  const onSearch = (value) => {
    setSearch(value);
  };



  // const { Option } = Select; 

  const onStatusChange = (managerId, status) => {
    if (!managerId || !status) {
      console.error("Invalid Manager ID");
      return;
    }
  
    axios
      .put(`http://localhost:5000/managers/${managerId}`, { status })
      .then((res) => {
        setManagers((prevState) =>
          prevState.map((manager) => {
            if (manager.id === managerId) {
              return { ...manager, status };
            } else {
              return manager;
            }
          })
        );
      })
      .catch((error) => {
        console.error("Update status error:", error);
      });
  };
  

  const onDeleteManager = (authId) => {
    if (!authId) {
      console.error("Invalid Manager ID");
      return;
    }
  
    axios
      .delete(`34.155.188.71/user/delete-manager/${authId}`)
      .then((res) => {
        setManagers((prevState) =>
          prevState.filter((manager) => manager.authId !== authId)
        );
      })
      .catch((error) => {
        console.error("Delete error:", error);
      });
  };
  
  
  
  const columns = [
    {
      title: "ID",
      dataIndex: "authId",
      key: "authId",
    },
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
    },
    {
      title: "Surname",
      dataIndex: "surname",
      key: "surname",
    },
    {
      title: "Email",
      dataIndex: "email",
      key: "email",
    },
    {
      title: "Company Name",
      dataIndex: "companyName",
      key: "companyName",
    },
    {
      title: "Tax Number",
      dataIndex: "taxNo",
      key: "taxNo",
    },
    {
      title: "Status",
      dataIndex: "status",
      key: "status",
    },
    {
      dataIndex: "authId",
      key: "authId",
      render: (cell, row) => {
        return (
          <>
            <Button
              type="primary"
              shape="circle"
              icon={<EditOutlined />}
              onClick={() => onClickEdit(row)}
            />
            <Button
              type="danger"
              shape="circle"
              icon={<DeleteOutlined />}
              onClick={() => onDeleteManager(row.authId)} 
            />
          </>
        );
      },
      width: 100,
    },
  ];

  useEffect(() => {
    axios.get("http://34.155.188.71/user/findall-manager").then((res) => {
      setManagers(res.data);
    });
  }, []);

  return (
    <PageLayoutManager buttons={buttons} onSearch={onSearch}>
      <Table
  dataSource={managers.filter((manager) => {
    return (
      manager && 
      manager.name && 
      manager.name.includes(search)
    );
  })}
  columns={columns}
  rowKey="id"
/>

      {isModalOpen && (
        <AddManagersModal
          isModalOpen={isModalOpen}
          onOk={onOkAddModel}
          onCancel={onCancelAddModel}
          initialValues={initialValues}
          managers={managers}
          onStatusChange={onStatusChange}
        />
      )}
    </PageLayoutManager>
  );
};

export default Managerlist;
