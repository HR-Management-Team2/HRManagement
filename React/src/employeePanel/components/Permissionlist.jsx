import { useEffect, useState } from "react";
import PageLayoutEmployee from "../components/PageLayoutEmployee";
import { Button, Table } from "antd";
import axios from "axios";
import { EditOutlined, DeleteOutlined } from "@ant-design/icons";
import AddPermissionsModal from "../components/AddPermissionsModal";
import dayjs from "dayjs";



const Permissionlist = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [permissions, setPermissions] = useState([]);
  const [initialValues, setInitialValues] = useState([]);
  const [search, setSearch] = useState("");

  const buttons = [
    {
      key: "addPermissions",
      text: "Add Permission",
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

    const days = calculatePermissionDays(values.startDate, values.endDate);
    function calculatePermissionDays(startDate, endDate) {
        const start = dayjs(startDate);
        const end = dayjs(endDate);
        const dayDifference = end.diff(start, "day");
        return dayDifference + 1;
      }

    if (initialValues) {
      axios
        .put(`http://localhost:5000/permissions/${initialValues.id}`, {
          ...values,
        })
        .then((res) => {
          setPermissions((prevState) => {
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
      const newPermissions = {
        permissionType: values.permissionType,
        dateOfRequest: values.dateOfRequest,
        startDate: values.startDate,
        endDate: values.endDate,
        days: days,
        replyDate: values.replyDate,
        status: values.status,
      };

      axios
        .post("http://localhost:5000/permissions", newPermissions)
        .then((res) => {
          setPermissions((prevState) => [
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
    row.establish = dayjs(row.establish);
    setInitialValues(row);
  };
  const onSearch = (value) => {
    setSearch(value);
  };

  // const { Option } = Select;

  const onStatusChange = (permissionId, status) => {
    if (!permissionId || !status) {
      console.error("Invalid Permission ID");
      return;
    }

    axios
      .put(`http://localhost:5000/permissions/${permissionId}`, { status })
      .then((res) => {
        setPermissions((prevState) =>
          prevState.map((permission) => {
            if (permission.id === permissionId) {
              return { ...permission, status };
            } else {
              return permission;
            }
          })
        );
      })
      .catch((error) => {
        console.error("Update status error:", error);
      });
  };

  const onDeletePermission = (permissionId) => {
    if (!permissionId) {
      console.error("Invalid Permission ID");
      return;
    }

    axios
      .delete(`http://localhost:5000/permissions/${permissionId}`)
      .then((res) => {
        setPermissions((prevState) =>
          prevState.filter((permission) => permission.id !== permissionId)
        );
      })
      .catch((error) => {
        console.error("Delete error:", error);
      });
  };

  const columns = [
    {
      title: "Permission Type",
      dataIndex: "permissionType",
      key: "permissionType",
    },
    {
      title: "Date of Request",
      dataIndex: "dateOfRequest",
      key: "dateOfRequest",
    },
    {
      title: "Start Date",
      dataIndex: "startDate",
      key: "startDate",
    },
    {
      title: "End Date",
      dataIndex: "endDate",
      key: "endDate",
    },
    {
      title: "Days",
      dataIndex: "days",
      key: "days",
    },
    {
      title: "Reply Date",
      dataIndex: "replyDate",
      key: "replyDate",
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
              onClick={() => onDeletePermission(row.id)}
            />
          </>
        );
      },
      width: 100,
    },
  ];

  useEffect(() => {
    axios.get("http://localhost:5000/permissions").then((res) => {
      setPermissions(res.data);
    });
  }, []);

  return (
    <PageLayoutEmployee buttons={buttons} onSearch={onSearch}>
      <Table
        dataSource={permissions.filter((permission) => {
          return (
            (permission && permission.permissionType) ||
            permission.description.includes(search)
          );
        })}
        columns={columns}
        rowKey="id"
      />

      {isModalOpen && (
        <AddPermissionsModal
          isModalOpen={isModalOpen}
          onOk={onOkAddModel}
          onCancel={onCancelAddModel}
          initialValues={initialValues}
          permissions={permissions}
          onStatusChange={onStatusChange}
        />
      )}
    </PageLayoutEmployee>
  );
};

export default Permissionlist;
