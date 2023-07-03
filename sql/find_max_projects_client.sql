SELECT client.id, client.name, COUNT(project.id) AS project_count
FROM client
LEFT JOIN project ON client.id = project.client_id
GROUP BY client.id, client.name
HAVING COUNT(project.id) = (
    SELECT MAX(project_count)
    FROM (
        SELECT client_id, COUNT(project.id) AS project_count
        FROM project
        GROUP BY client_id
    ) AS project_counts
);
