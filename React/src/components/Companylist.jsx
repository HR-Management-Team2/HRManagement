
import { useEffect, useState } from "react";
import PageLayout from "./PageLayout";
import { Button, Table } from "antd";
import axios from "axios";
import { EditOutlined, DeleteOutlined } from "@ant-design/icons";
import AddCompaniesModal from "./AddCompaniesModal";
import dayjs from 'dayjs';

const Companylist = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [companies, setCompanies] = useState([]);
  const [initialValues, setInitialValues] = useState([]);
  const [search, setSearch] = useState("");

  const buttons = [
    {
      key: "addCompanies",
      text: "Add Company",
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

    // if (initialValues) {
    //   axios
    //     .put(`http://localhost:9080/api/v1/company/update/${initialValues.taxNumber}`, {
    //       ...values
    //     })
    //     .then((res) => {
    //       setCompanies((prevState) => {
    //         return prevState.map((u) => {
    //           if (res.data.taxNumber === u.taxNumber) {
    //             return {
    //               ...res.data,
    //             };
    //           } 
    //           return u;
    //         });
    //       });
    //     });
    // } else {
    //   const newCompanies = {
    //     name: values.name,
    //     taxNumber: values.taxNumber,
    //     phone: values.phone,
    //     address: values.address,
    //     yearOfEstablishment: values.yearOfEstablishment
    //   };
    //   axios.post("http://localhost:9080/api/v1/company/addcompany", newCompanies).then((res) => {
    //     setCompanies((prevState) => [
    //       ...prevState,
    //       {
    //         ...res.data, 
    //       },
    //     ]);
    //   });
    // }

    if (initialValues) {
      axios
        .put(`http://34.155.188.71/company/update/${initialValues.taxNumber}`, {
          ...values
        })
        .then((res) => {
          setCompanies((prevState) => {
            return prevState.map((u) => {
              if (res.data.taxNumber === u.taxNumber) {
                return {
                  ...res.data,
                };
              }
              return u;
            });
          });
        });
    } else {
      const data = JSON.stringify({
        name: values.name,
        taxNumber: values.taxNumber,
        phone: values.phone,
        address: values.address,
        email: values.email,
        yearOfEstablishment: values.yearOfEstablishment
      })

      const header = {
        'Content-Type': 'application/json',
        // 'Accept-Encoding': 'gzip;q=1.0, compress;q=0.5',
      };

      axios({
        method: 'POST',
        url: 'http://34.155.188.71/company/addcompany',
        headers: header,
        data: data
      }).then(result => {
        console.log(result);
      }).catch(data => {
        const result = data.response.data;
        alert(result.message);
      })
    }

  };

  const onCancelAddModel = () => {
    setIsModalOpen(false);
  };

  const onClickEdit = (row) => {
    setIsModalOpen(true);
    row.establish = dayjs(row.establish)
    setInitialValues(row);
  };
  const onSearch = (value) => {
    setSearch(value);
  };

  const onDeleteCompany = (taxNumber) => {
    if (!taxNumber) {
      console.error("Invalid Company Tax Number");
      return;
    }

    axios
      .delete(`http://34.155.188.71/company/delete/${taxNumber}`)
      .then((res) => {
        if (res.status === 200) {
          console.log("Company deleted successfully.");
          setCompanies((prevState) =>
            prevState.filter((company) => company.taxNumber !== taxNumber)
          );
        } else {
          console.error("Delete error:", res.data);
        }
      })
      .catch((error) => {
        console.error("Delete error:", error);
      });
  };


  const columns = [
    {
      title: "Company Name",
      dataIndex: "name",
      key: "name",
    },
    {
      title: "Tax Number",
      dataIndex: "taxNumber",
      key: "taxNumber",
    },
    {
      title: "Phone",
      dataIndex: "phone",
      key: "phone",
    },
    {
      title: "Address",
      dataIndex: "address",
      key: "address",
    },
    {
      title: "Year of Establish",
      dataIndex: "yearOfEstablishment",
      key: "yearOfEstablishment",
    },

    {
      dataIndex: "taxNumber",
      key: "taxNumber",
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
              onClick={() => onDeleteCompany(row.taxNumber)}
            />
          </>

        );
      },
      width: 100,
    },
  ];

  useEffect(() => {
    axios.get("http://34.155.188.71/company/findall").then((res) => {
      setCompanies(res.data);
    });
  }, []);

  return (
    <PageLayout buttons={buttons} onSearch={onSearch}>
      <Table
        dataSource={companies.filter((company) => {
          return (
            company.name.includes(search)
          );
        })}
        columns={columns}
        rowKey="id"
      />
      {isModalOpen && (
        <AddCompaniesModal
          isModalOpen={isModalOpen}
          onOk={onOkAddModel}
          onCancel={onCancelAddModel}
          initialValues={initialValues}
        />
      )}
    </PageLayout>
  );
};

export default Companylist;
