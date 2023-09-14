
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
        .put(`http://localhost:5000/managers/${initialValues.id}`, {
          ...values
        })
        .then((res) => {
          setManagers((prevState) => {
            return prevState.map((u) => {
              if (res.data.id === u.id) {
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
        companyname: values.companyname,
        taxno: values.taxno,
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
  

  const onDeleteManager = (managerId) => {
    if (!managerId) {
      console.error("Invalid Manager ID");
      return;
    }
  
    axios
      .delete(`http://localhost:5000/managers/${managerId}`)
      .then((res) => {
        setManagers((prevState) =>
          prevState.filter((manager) => manager.id !== managerId)
        );
      })
      .catch((error) => {
        console.error("Delete error:", error);
      });
  };
  
  
  
  const columns = [
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
      dataIndex: "companyname",
      key: "companyname",
    },
    {
      title: "Tax Number",
      dataIndex: "taxno",
      key: "taxno",
    },
    {
      title: "Status",
      dataIndex: "status",
      key: "status",
    },
    {
      dataIndex: "id",
      key: "id",
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
              onClick={() => onDeleteManager(row.id)} 
            />
          </>
        );
      },
      width: 100,
    },
  ];

  useEffect(() => {
    axios.get("http://localhost:5000/managers").then((res) => {
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
