async function addReimb() {
  const reimb = {
    name: document.getElementById("monName").value,
    type: {
      type: document.getElementById("monType").value,
      furry: document.getElementById("monFur").value,
      paws: document.getElementById("monPaws").value,
    },
  };
  const fetched = await fetch("http://localhost:8080/HallowsMonsters/monster.json", {
    method: "post",
    body: JSON.stringify(monster),
  });
  const json = await fetched.text();
  const rows = document.getElementById('hallowsTableBody').innerHTML='';
  asyncFetch("http://localhost:8080/HallowsMonsters/all.json", renderTable);
 }

document.getElementById("monSubmit").addEventListener("click", addMonster);
 